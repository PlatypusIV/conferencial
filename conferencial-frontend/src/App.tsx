import { useEffect } from 'react';
import { ConfigProvider, theme } from 'antd';
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';
import { getRequest } from './util/rest';
import { setConferences } from './store/conferenceActions';
import urls from "./util/urls.json";
import { useAppDispatch, useSelectedMonth } from './store/hooks';
import dayjs from 'dayjs';
import ConferenceCreationModal from './components/conferenceCreationModal/ConferenceCreationModal';
import { setRooms } from './store/roomActions';
import ConferenceEditingModal from './components/conferenceEditingModal/ConferenceEditingModal';
import './App.css';

function App() {
  const selectedMonth = useSelectedMonth();
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
      <ConfigProvider
          theme={{
          algorithm: theme.darkAlgorithm,
          components: {
            Modal: {
              colorBgBase: '#141414'
            },

    },
      }}>
        <div className='headerContainer'>
          <Header />
        </div>
        <div className='content'>
          <CalendarContainer/>
        </div>
        <ConferenceCreationModal refreshConferences={refreshConferences} />
        <ConferenceEditingModal />
      </ConfigProvider>
    </>
  )
}

export default App
