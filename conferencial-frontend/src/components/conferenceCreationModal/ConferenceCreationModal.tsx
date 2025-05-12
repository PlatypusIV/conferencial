import { useState } from 'react';
import { Modal, Form, Input, TimePicker, Select, Button, message, Space } from 'antd';
import { useAppDispatch, useAppSelector, useRooms, useSelectedDate } from '../../store/hooks';
import { setIsConferenceCreationModalOpen } from '../../store/userInterfaceActions';
import type { Conference } from '../../util/interfaces';
import { postRequest } from '../../util/rest';
import urls from "../../util/urls.json";
import dayjs from 'dayjs';
import type { DefaultOptionType } from 'antd/es/select';


interface Props {
  refreshConferences: () => Promise<void>;
}

const emptyConference: Conference = {
  id:0,
  name:"",
  roomId:0,
  startTime:"",
  endTime: ""
}

export default function ConferenceCreationModal(props: Props) {
  const [form] = Form.useForm();
  const [messageApi, contextHolder] = message.useMessage();
  const isOpen = useAppSelector((state)=> state.userInterface.isConferenceCreationModalOpen);
  const dispatch = useAppDispatch();
  const rooms = useRooms();
  const selectedDate = useSelectedDate();
  const [createdConference, setCreatedConference] = useState<Conference>(emptyConference);

  function createRoomOptionsArray() {
    return rooms.map(r => {return {value: r.id, label: r.name}}) as DefaultOptionType[];
  };

  const handleOk = async () => {
    try {
      
      await postRequest(`${urls.conferences}/`,createdConference);
      messageApi.success("Conference successfully created.");
      await props.refreshConferences();
      setCreatedConference(emptyConference);
      dispatch(setIsConferenceCreationModalOpen(false));
    } catch (error) {
      messageApi.error((error as Error).message)
    }
  };

  const handleCancel = () => {
    setCreatedConference(emptyConference);
    dispatch(setIsConferenceCreationModalOpen(false));
  }

  function createDateTime(start?: string, end?: string) {
    const date = dayjs(selectedDate).format("YYYY-MM-DD");

    const startTime = `${date}T${start}`;
    const endTime = `${date}T${end}`;

    setCreatedConference({...createdConference, startTime,endTime});
  }

  return (
    <Modal
        styles={{
        body: { backgroundColor: '#141414' },
    }}
        className='conferenceCreationModal'
        title="Create conference"
        open={isOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        footer={(_, {CancelBtn }) => (
          <>
          {contextHolder}
            <Form name='createConference' form={form} onFinish={handleOk} >
              <Form.Item 
                label="Conference Name"
                name="conferenceName"
                rules={[{required: true, message: "Please input conference name", max:150}]}
               >
                <Input placeholder='Example conference' onChange={(newValue)=> setCreatedConference({...createdConference, name:newValue.target.value})} value={createdConference?.name}/>
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
                <Select options={createRoomOptionsArray()} onSelect={(newValue)=>setCreatedConference({...createdConference, roomId:newValue})} className='roomSelectionDropdown'/>
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