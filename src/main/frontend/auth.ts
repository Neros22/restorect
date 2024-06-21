import {configureAuth} from '@vaadin/hilla-react-auth';
import {UserEndpoint} from './generated/endpoints';
import {
    login as loginImpl,
    LoginOptions,
    LoginResult,
    logout as logoutImpl,
    LogoutOptions
} from '@vaadin/hilla-frontend';

// Configure auth to use `UserInfoService.getUserInfo`
const auth = configureAuth(UserEndpoint.getUserInfo);

// Export auth provider and useAuth hook, which are automatically
// typed to the result of `UserInfoService.getUserInfo`
export const useAuth = auth.useAuth;
export const AuthProvider = auth.AuthProvider;


export async function login(username: string, password: string, options: LoginOptions = {}): Promise<LoginResult> {
    return await loginImpl(username, password, {
        ...options,
        async onSuccess() {
            // Get user info from endpoint
            await UserEndpoint.getUserInfo();
        },
    });
}

/**
 * Logout wrapper method that retrieves user information.
 */
export async function logout(options: LogoutOptions = {}) {
    await logoutImpl({
        ...options,
        onSuccess() {
            //UserEndpoint.clearUserInfo();
        },
    });
}