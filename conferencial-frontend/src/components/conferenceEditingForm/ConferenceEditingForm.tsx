import React from 'react';
import { useAppDispatch, useIsConferenceEditingFormOpen, useParticipants, useRooms, useSelectedConference } from '../../store/hooks';
import { Button, Form, Input, Modal, message } from 'antd';
import dayjs from 'dayjs';
import './ConferenceEditingForm.css';
import { setIsConferenceEditingFormOpen } from '../../store/userInterfaceActions';
import { setSelectedConference } from '../../store/conferenceActions';

export default function ConferenceEditingForm() {
    const dispatch = useAppDispatch();
    const isConferenceEditingFormOpen = useIsConferenceEditingFormOpen();
    const selectedConference = useSelectedConference();
    const participants = useParticipants();
    const rooms = useRooms();


    function displayRoomInfo(){
        const selectedRoom = rooms.find(r=> r.id === selectedConference?.roomId);
        return `${selectedRoom?.name} - seats: ${selectedRoom?.maxSeats}`;
    }

    function cancelConference(){
        try {
            console.log(selectedConference);

            closeModal();
        } catch (error) {
            console.log((error as Error).message);
        }
    }

    function closeModal() {
        dispatch(setSelectedConference(undefined))
        dispatch(setIsConferenceEditingFormOpen(false));
    }

  return (
    <Modal open={isConferenceEditingFormOpen} onCancel={closeModal} onOk={closeModal}>
        <div className='conferenceEditingContainer'>
            <Form onFinish={cancelConference}>
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
                    <Button danger={true}> Cancel conference</Button>
                </Form.Item>
            </Form>
        </div>

    </Modal>
  )
}