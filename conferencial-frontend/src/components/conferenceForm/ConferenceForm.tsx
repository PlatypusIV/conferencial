import React, { useEffect, useState } from 'react';
import { Modal, Form, Input, TimePicker, Select, Button, message, Space } from 'antd';
import { useAppDispatch, useAppSelector, useRooms, useSelectedDate } from '../../store/hooks';
import { setConferenceFormIsOpen, setIsError, setMessageText } from '../../store/userInterfaceActions';
import type { Conference } from '../../util/interfaces';
import { postRequest } from '../../util/rest';
import urls from "../../util/urls.json";
import dayjs from 'dayjs';
import type { DefaultOptionType } from 'antd/es/select';
import handleResponseError from '../../util/errorHandler';


interface Props {
  refreshConferences: () => Promise<void>;
}

const emptyConference: Conference = {
  name:"",
  roomId:0,
  startTime:"",
  endTime: ""
}

export default function ConferenceForm(props: Props) {
  const [form] = Form.useForm();
  const isOpen = useAppSelector((state)=> state.userInterface.isConferenceFormOpen);
  const dispatch = useAppDispatch();
  const rooms = useRooms();
  const selectedDate = useSelectedDate();
  const [createdConference, setCreatedConference] = useState<Conference>(emptyConference);

  useEffect(()=>{
    console.log("created conference: " + createdConference);
  }, []);

  function createRoomOptionsArray() {
    return rooms.map(r => {return {value: r.id, label: r.name}}) as DefaultOptionType[];
  };

  const handleOk = async () => {
    try {
      
      await postRequest(`${urls.conferences}/`,createdConference);
      dispatch(setIsError(false));
      dispatch(setMessageText("Conference successfully created."));
      await props.refreshConferences();
      setCreatedConference(emptyConference);
      dispatch(setConferenceFormIsOpen(false));
    } catch (error) {
      message.error((error as Error).message)
      // handleResponseError(dispatch,)
    }
    
  };

  const handleCancel = () => {
    dispatch(setConferenceFormIsOpen(false));
    setCreatedConference(emptyConference);
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
        footer={(_, {CancelBtn }) => (
          <>
            <Form name='createConference' form={form} onFinish={handleOk}>
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
               <Form.Item>
                  <Space>
                  <CancelBtn />
                  <Button type="primary" htmlType="submit">
                    Submit
                  </Button>
                  </Space>
               </Form.Item>
               
            </Form>
            
          </>
        )}
      >
      </Modal>
  )
}