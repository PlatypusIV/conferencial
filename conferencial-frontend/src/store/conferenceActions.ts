import {createSlice} from '@reduxjs/toolkit'
import type { Conference } from '../util/interfaces';

interface ConferenceState {
    conferences: Conference[];
    selectedConference?: Conference;
}

const initialState: ConferenceState = {
    conferences: [],
    selectedConference: undefined,
}

const conferenceSlice = createSlice({
    name:'conference',
    initialState,
    reducers:{
        setConferences: (state: ConferenceState, action) => {
            state.conferences = action.payload;
        },
        setSelectedConference: (state: ConferenceState, action) => {
            state.selectedConference = action.payload;
        }
    }
});

export const {setConferences, setSelectedConference} = conferenceSlice.actions;
export default conferenceSlice.reducer;