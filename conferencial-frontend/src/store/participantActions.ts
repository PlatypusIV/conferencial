import {createSlice} from '@reduxjs/toolkit'
import type { Participant } from '../util/interfaces';

interface ParticipantState {
    participants: Participant[];
};

const initialState: ParticipantState = {
    participants: []
}

const participantSlice = createSlice({
    name:'participant',
    initialState,
    reducers:{
        setParticipants: (state, action) => {
            state = action.payload;
        }
    }
});

export const {setParticipants} = participantSlice.actions;
export default participantSlice.reducer;