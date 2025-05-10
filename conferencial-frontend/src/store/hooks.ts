import { useDispatch, useSelector } from 'react-redux';
import type { RootState, AppDispatch } from './store';

export const useAppDispatch = useDispatch.withTypes<AppDispatch>();
export const useAppSelector = useSelector.withTypes<RootState>();

export const useConferences = () => useAppSelector((state)=> state.conference.conferences);
export const useSelectedConference = () => useAppSelector((state)=> state.conference.selectedConference);
export const useParticipants = () => useAppSelector((state)=> state.participant.participants);
export const useRooms = () => useAppSelector((state)=> state.room.rooms);
export const useSelectedDate = () => useAppSelector((state)=> state.userInterface.selectedDate);
export const useSelectedMonth = () => useAppSelector((state)=> state.userInterface.selectedMonth);
export const useIsConferenceEditingFormOpen = () => useAppSelector((state)=> state.userInterface.isConferenceEditingFormOpen);
