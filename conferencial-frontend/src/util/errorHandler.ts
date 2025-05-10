import { setIsError, setMessageText } from "../store/userInterfaceActions";

function handleResponseError(dispatch: (arg0: { payload: unknown; type: "userInterface/setIsError" | "userInterface/setMessageText"; }) => void, error: string | unknown): void {
    dispatch(setIsError(true));
    dispatch(setMessageText(error));
}

export default handleResponseError;