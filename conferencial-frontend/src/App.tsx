import { useEffect } from 'react'
import './App.css'
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';
import { getRequest } from './util/rest';
import { setConferences } from './store/conferenceActions';
import urls from "./util/urls.json";
import { useAppDispatch, useConferences } from './store/hooks';
import dayjs from 'dayjs';
import ConferenceForm from './components/conferenceForm/ConferenceForm';
import { setRooms } from './store/roomActions';
import FeedbackMessage from './components/feedbackMessage/FeedbackMessage';


function App() {
  const conferences = useConferences();
  const dispatch = useAppDispatch();

  useEffect(()=>{
    init();
  },[]);

  async function init() {
    const startOfCurrentMonth = dayjs().startOf('month').format('YYYY-MM-DDTHH:mm');
    const endOfCurrentMonth = dayjs().endOf('month').format('YYYY-MM-DDTHH:mm');
    const conferencesThatMonth = await getRequest(`${urls.conferences}?start=${startOfCurrentMonth}&end=${endOfCurrentMonth}`);
    const rooms = await getRequest(urls.rooms);
    
    dispatch(setConferences(conferencesThatMonth));
    dispatch(setRooms(rooms));
  }

  return (
    <>
      <Header></Header>
      {conferences.length? <CalendarContainer/> : <div></div>}
      <ConferenceForm></ConferenceForm>
      <FeedbackMessage />
    </>
  )
}

export default App
