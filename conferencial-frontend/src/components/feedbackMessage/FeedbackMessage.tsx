import React, { useEffect } from 'react';
import { message } from 'antd';
import { useAppSelector } from '../../store/hooks';

export default function FeedbackMessage() {
    const [messageApi, contextHolder] = message.useMessage();
    const messageText = useAppSelector((state) => state.userInterface.messageText);
    const isError = useAppSelector((state)=> state.userInterface.isError);

    useEffect(()=>{
        if(messageText?.length){
            openMessage();
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [messageText]);

    const openMessage = () => {
        messageApi.open({
          type: isError ? "error" : "success",
          content: messageText,
        });
      };

  return (
    <>
        {contextHolder}
    </>
  )
}
