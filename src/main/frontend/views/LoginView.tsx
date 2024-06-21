import {LoginOverlay} from '@vaadin/react-components/LoginOverlay.js';
import {login} from '../auth';
import {useSignal} from "@vaadin/hilla-react-signals";

export default function LoginView() {
    const hasError = useSignal<boolean>(false);

    return (
        <LoginOverlay
            opened
            error={hasError.value}
            onLogin={async ({detail: {username, password}}) => {
                const {error} = await login(username, password);
                hasError.value = Boolean(error);
            }}
        />
    );
}