import {createSlice} from '@reduxjs/toolkit';

interface InitialState {
    isConferenceFormOpen: boolean;
    selectedDate?: string;
}

const initialState: InitialState = {
    isConferenceFormOpen: false,
    selectedDate: undefined
}

const userInterfaceSlice = createSlice({
    name:'userInterface',
    initialState,
    reducers:{
        setConferenceFormIsOpen: (state, action) => {
            state.isConferenceFormOpen = action.payload;
        },
        setSelectedDate: (state, action) => {
            state.selectedDate = action.payload;
        }

    }
});

export const {setConferenceFormIsOpen, setSelectedDate} = userInterfaceSlice.actions;
export default userInterfaceSlice.reducer;