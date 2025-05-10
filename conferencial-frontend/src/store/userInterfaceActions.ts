import {createSlice} from '@reduxjs/toolkit';

interface InitialState {
    isConferenceFormOpen: boolean;
    isConferenceEditingFormOpen: boolean;
    selectedDate?: string;
    selectedMonth?: number;
}

const initialState: InitialState = {
    isConferenceFormOpen: false,
    isConferenceEditingFormOpen: false,
    selectedDate: undefined,
    selectedMonth: undefined,
}

const userInterfaceSlice = createSlice({
    name:'userInterface',
    initialState,
    reducers:{
        setIsConferenceFormOpen: (state, action) => {
            state.isConferenceFormOpen = action.payload;
        },
        setIsConferenceEditingFormOpen: (state, action) => {
            state.isConferenceEditingFormOpen = action.payload;
        },
        setSelectedDate: (state, action) => {
            state.selectedDate = action.payload;
        },
        setSelectedMonth: (state, action) => {
            state.selectedMonth = action.payload;
        }
    }
});

export const {setIsConferenceFormOpen, setSelectedDate, setSelectedMonth, setIsConferenceEditingFormOpen} = userInterfaceSlice.actions;
export default userInterfaceSlice.reducer;