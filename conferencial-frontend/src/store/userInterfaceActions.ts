import {createSlice} from '@reduxjs/toolkit';

interface InitialState {
    isConferenceFormOpen: boolean;
    selectedDate?: string;
    selectedMonth?: number;
    isError?: boolean;
    messageText?: string;
}

const initialState: InitialState = {
    isConferenceFormOpen: false,
    selectedDate: undefined,
    selectedMonth: undefined,
    isError: false,
    messageText: "",
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
        },
        setSelectedMonth: (state, action) => {
            state.selectedMonth = action.payload;
        },
        setIsError: (state, action) => {
            state.isError = action.payload;
        },
        setMessageText: (state, action) => {
            state.messageText = action.payload;
        }
    }
});

export const {setConferenceFormIsOpen, setSelectedDate, setSelectedMonth, setIsError, setMessageText} = userInterfaceSlice.actions;
export default userInterfaceSlice.reducer;