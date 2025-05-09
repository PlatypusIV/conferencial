import React, { useEffect, useState } from 'react';
import { Button, Modal, Space, Form, Input, TimePicker, Select } from 'antd';
import { useAppDispatch, useAppSelector, useConferences, useRooms, useSelectedDate } from '../../store/hooks';
import { setConferenceFormIsOpen } from '../../store/userInterfaceActions';
import type { Conference, Room } from '../../util/interfaces';
import { getRequest, postRequest } from '../../util/rest';
import urls from "../../util/urls.json";
import dayjs, { Dayjs } from 'dayjs';
import type { DefaultOptionType } from 'antd/es/select';

const emptyConference: Conference = {
  name:"",
  roomId:0,
  startTime:"",
  endTime: ""
}

export default function ConferenceForm() {
  const isOpen = useAppSelector((state)=> state.userInterface.isConferenceFormOpen);
  const dispatch = useAppDispatch();
  const rooms = useRooms();
  const conferences = useConferences();
  const selectedDate = useSelectedDate();
  const [createdConference, setCreatedConference] = useState<Conference>(emptyConference);

  useEffect(()=> {
    console.log(createdConference);
  },[createdConference]);

  useEffect(()=> {
    console.log(selectedDate);
  },[selectedDate]);

  function createRoomOptionsArray() {
    return rooms.map(r => {return {value: r.id, label: r.name}}) as DefaultOptionType[];
  };

  const handleOk = async () => {
    console.log(createdConference);
    const response = await postRequest(`${urls.conferences}/`,createdConference);
    console.log(response.status);
    dispatch(setConferenceFormIsOpen(false));
  };

  const handleCancel = () => {
    dispatch(setConferenceFormIsOpen(false));
  }

  function createDateTime(start?: string, end?: string) {
    const date = dayjs(selectedDate).format("YYYY-MM-DD");

    const startTime = `${date}T${start}`;
    const endTime = `${date}T${end}`;

    setCreatedConference({...createdConference, startTime,endTime});
  }

  return (
    <Modal
        title="Create conference"
        open={isOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        footer={(_, { OkBtn, CancelBtn }) => (
          <>
            <Form name='createConference'>
              <Form.Item 
                label="Conference Name"
                name="conferenceName"
                rules={[{required: true, message: "Please input conference name"}]}
               >
                <Input placeholder='Example conference' onChange={(newValue)=> setCreatedConference({...createdConference, name:newValue.target.value})}/>
               </Form.Item>

              <Form.Item 
                label="Duration"
                name="duration"
                rules={[{required: true, message: "Please input conference duration"}]}
               >
                <TimePicker.RangePicker format={'HH:mm'} value={[dayjs(selectedDate), dayjs(selectedDate)]} onChange={(newValue)=> createDateTime(newValue?.[0]?.format("HH:mm"), newValue?.[1]?.format("HH:mm"))}/>
               </Form.Item>
               <Form.Item 
                label="Room"
                name="room"
                rules={[{required: true, message: "Please select a room"}]}
               >
                <Select options={createRoomOptionsArray()} onSelect={(newValue)=>setCreatedConference({...createdConference, roomId:newValue})}/>
               </Form.Item>
            </Form>

            <CancelBtn />
            <OkBtn/>
          </>
        )}
      >
      </Modal>
  )
}