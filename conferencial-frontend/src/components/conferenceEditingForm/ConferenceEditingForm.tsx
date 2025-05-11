import React, { useEffect } from 'react';
import { useAppDispatch, useIsConferenceEditingFormOpen, useParticipants, useRooms, useSelectedConference } from '../../store/hooks';
import type { PopconfirmProps } from 'antd';
import { Button, Form, Input, Modal, message, Popconfirm } from 'antd';
import dayjs from 'dayjs';
import urls from "../../util/urls.json";
import { setIsConferenceEditingFormOpen } from '../../store/userInterfaceActions';
import { setSelectedConference } from '../../store/conferenceActions';
import { getRequest, patchRequest } from '../../util/rest';
import './ConferenceEditingForm.css';
import type { Conference } from '../../util/interfaces';


export default function ConferenceEditingForm() {
    const dispatch = useAppDispatch();
    const [messageApi, contextHolder] = message.useMessage();
    const isConferenceEditingFormOpen = useIsConferenceEditingFormOpen();
    const selectedConference = useSelectedConference();
    const participants = useParticipants();
    const rooms = useRooms();

    useEffect(()=>{
        getConferenceData();
    },[]);


    async function getConferenceData(){
        try {
            const conferenceResponse = await getRequest(`${urls.conferences}/${selectedConference?.id}`);

            dispatch(setSelectedConference(conferenceResponse));

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
        dispatch(setSelectedConference(undefined))
        dispatch(setIsConferenceEditingFormOpen(false));
    }

  return (
    <Modal open={isConferenceEditingFormOpen} onCancel={closeModal} onOk={closeModal}>
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
        </div>
    </Modal>
  )
}