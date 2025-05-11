import React, { useEffect, useState } from 'react';
import { useAppDispatch, useIsConferenceEditingFormOpen, useParticipants, useRooms, useSelectedConference } from '../../store/hooks';
import { Button, Form, Input, Modal, message, Popconfirm, DatePicker, List } from 'antd';
import Search from 'antd/es/input/Search';
import dayjs from 'dayjs';
import urls from "../../util/urls.json";
import { setIsConferenceEditingFormOpen } from '../../store/userInterfaceActions';
import { setSelectedConference } from '../../store/conferenceActions';
import { getRequest, patchRequest, postRequest } from '../../util/rest';
import './ConferenceEditingForm.css';
import type { Conference, Participant } from '../../util/interfaces';
import { setParticipants } from '../../store/participantActions';

const emptyParticipant = {
    fullName: "",
    conferenceId: 0,
    birthDate: ""
}

export default function ConferenceEditingForm() {
    const dispatch = useAppDispatch();
    const [messageApi, contextHolder] = message.useMessage();
    const [currentParticipant, setCurrentParticipant] = useState<Participant>(emptyParticipant);
    const isConferenceEditingFormOpen = useIsConferenceEditingFormOpen();
    const selectedConference = useSelectedConference();
    const participants = useParticipants();
    const [searchTerm, setSearchTerm] = useState<string>("");
    const [searchableParticipantList, setSearchableParticipantList] = useState<Participant[]>([]);
    const rooms = useRooms();

    useEffect(()=>{
        getConferenceData();
    },[]);

    async function addNewParticipantToConference(){
        try {
            const response = await postRequest<Participant>(`${urls.participants}`, {...currentParticipant, conferenceId: selectedConference?.id});
            messageApi.success(`${response.fullName} has been added to ${selectedConference?.name}`);
        } catch (error) {
            messageApi.error((error as Error).message);
        }
    }

    function searchForParticipant(){
        
    }

    async function getConferenceData(){
        try {
            const conferenceResponse = await getRequest(`${urls.conferences}/${selectedConference?.id}`);
            const participantsOfConference = await getRequest(`${urls.participants}/${selectedConference?.id}`);

            dispatch(setSelectedConference(conferenceResponse));
            dispatch(setParticipants(participantsOfConference));

        } catch (error) {
            messageApi.error((error as Error).message);
        }
    }

    function displayRoomInfo(){
        const selectedRoom = rooms.find(r=> r.id === selectedConference?.roomId);
        return `${selectedRoom?.name} - seats: ${selectedRoom?.maxSeats}`;
    }

    async function cancelConference(){
        try {
            const response = await patchRequest<Conference>(`${urls.conferences}/cancel/${selectedConference?.id}`, selectedConference);

            dispatch(setSelectedConference(response));
            messageApi.success(`Conference: ${response?.name} successfully ${response?.canceled? "canceled" : "reactivated"}`);
        } catch (error) {
            messageApi.error((error as Error).message)
        }
    }

    function closeModal() {
        setCurrentParticipant(emptyParticipant);
        dispatch(setSelectedConference(undefined))
        dispatch(setIsConferenceEditingFormOpen(false));
    }

  return (
    <Modal open={isConferenceEditingFormOpen} onCancel={closeModal} onOk={closeModal} className='conferenceEditingModal'>
        {contextHolder}
        <div className='conferenceEditingContainer'>
            <Form>
                <Form.Item 
                    label="Conference Name"
                    name="conferenceName"
                >
                    <Input placeholder={selectedConference?.name || "Empty"} disabled={true}/>
                </Form.Item>
                <Form.Item 
                    label="Duration"
                    name="duration"
                >
                    <Input placeholder={`start: ${dayjs(selectedConference?.startTime).format("HH:mm")} - end: ${dayjs(selectedConference?.endTime).format("HH:mm")}`} disabled={true}/>
                </Form.Item>
                <Form.Item 
                    label="Room"
                    name="room"
                >
                    <Input type="text" placeholder={displayRoomInfo()}/>
                </Form.Item>
                <Form.Item 
                    label="Cancel conference"
                    name="isCanceled"
                >
                    <Popconfirm title="Cancel conference" description="Are you sure you want to cancel the conference?" onConfirm={cancelConference} onCancel={(e)=> console.log("cancel action: " + e)}
                    okText="Yes"
                    cancelText="No"
                    >
                        <Button type={"primary"} danger={!selectedConference?.canceled} >{selectedConference?.canceled ? "Reactivate" : "Cancel"}</Button>
                    </Popconfirm> 
                </Form.Item>
            </Form>

            <Form onFinish={addNewParticipantToConference}>
                <Form.Item label="Participant full name">
                    <Input placeholder={currentParticipant?.fullName || "Insert full name"} onChange={(e)=> setCurrentParticipant({...currentParticipant, fullName:e.target.value})} value={currentParticipant?.fullName || ""}/>
                </Form.Item>
                <Form.Item label="Birthdate">
                    <DatePicker value={currentParticipant.birthDate ? dayjs(currentParticipant.birthDate) : dayjs()} onChange={(value)=>setCurrentParticipant({...currentParticipant, birthDate: value.format("YYYY-MM-DD").toString()})}/>
                </Form.Item>
                <Form.Item>
                    <Button type='primary' htmlType="submit">Add to participant list</Button>
                </Form.Item>
            </Form>
            <div className='participantListContainer'>
                <Search />
                <List />
            </div>
        </div>
    </Modal>
  )
}