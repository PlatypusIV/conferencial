import { useEffect } from 'react'
import './App.css'
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';
import { getRequest } from './util/rest';
import { setConferences } from './store/conferenceActions';
import urls from "./util/urls.json";
import { useAppDispatch, useAppSelector } from './store/hooks';
import dayjs from 'dayjs';
import ConferenceCreationModal from './components/conferenceCreationModal/ConferenceCreationModal';
import { setRooms } from './store/roomActions';
import ConferenceEditingModal from './components/conferenceEditingModal/ConferenceEditingModal';


function App() {
  const selectedMonth = useAppSelector((state)=>state.userInterface.selectedMonth);
  const dispatch = useAppDispatch();

  useEffect(()=>{
    init();
  },[]);

  useEffect(()=>{
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
      <ConferenceCreationModal refreshConferences={refreshConferences} />
      <ConferenceEditingModal />
    </>
  )
}

export default App
