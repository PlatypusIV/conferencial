import { useEffect } from 'react'
import './App.css'
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';
import { getRequest } from './util/rest';
import { setConferences } from './store/conferenceActions';
import urls from "./util/urls.json";
import { useAppDispatch, useAppSelector, useConferences } from './store/hooks';
import dayjs from 'dayjs';
import ConferenceForm from './components/conferenceForm/ConferenceForm';
import { setRooms } from './store/roomActions';
import FeedbackMessage from './components/feedbackMessage/FeedbackMessage';


function App() {
  const conferences = useConferences();
  const selectedMonth = useAppSelector((state)=>state.userInterface.selectedMonth);
  const dispatch = useAppDispatch();

  useEffect(()=>{
    init();
  },[]);

  useEffect(()=>{
    console.log("selectedmonth has changed: " + selectedMonth);
    refreshConferences();
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[selectedMonth]);

  async function init() {
    const startOfCurrentMonth = dayjs().startOf('month').format('YYYY-MM-DDTHH:mm');
    const endOfCurrentMonth = dayjs().endOf('month').format('YYYY-MM-DDTHH:mm');
    const conferencesThatMonth = await getRequest(`${urls.conferences}?start=${startOfCurrentMonth}&end=${endOfCurrentMonth}`);
    
    const rooms = await getRequest(urls.rooms);
    
    dispatch(setConferences(conferencesThatMonth));
    dispatch(setRooms(rooms));
  }

  async function refreshConferences(){
    const startOfMonth = dayjs().month(selectedMonth || dayjs().month()).startOf('month').format('YYYY-MM-DDTHH:mm');
    const endOfMonth = dayjs().month(selectedMonth || dayjs().month()).endOf('month').format('YYYY-MM-DDTHH:mm');
    const conferencesThatMonth = await getRequest(`${urls.conferences}?start=${startOfMonth}&end=${endOfMonth}`);
    
    dispatch(setConferences(conferencesThatMonth));
  }

  return (
    <>
      <Header></Header>
      <CalendarContainer/>
      <ConferenceForm refreshConferences={refreshConferences}></ConferenceForm>
      <FeedbackMessage />
    </>
  )
}

export default App
