import { useAppDispatch } from "../store/hooks";
import { setMessageText } from "../store/userInterfaceActions";



function useHandleResponseError(error: string | unknown): void {
    const dispatch = useAppDispatch();
    dispatch(setMessageText(error));
}

export default useHandleResponseError;