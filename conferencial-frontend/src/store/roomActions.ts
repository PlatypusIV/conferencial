import {createSlice} from '@reduxjs/toolkit'
import type { Room } from '../util/interfaces';

interface RoomState {
    rooms: Room[];
};

const initialState: RoomState = {
    rooms: []
}

const roomSlice = createSlice({
    name:'room',
    initialState,
    reducers:{
        setRooms: (state, action) => {
            state.rooms = action.payload;
        }
    }
});

export const {setRooms} = roomSlice.actions;
export default roomSlice.reducer;