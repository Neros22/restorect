import ApplicationAppBar from "../components/ApplicationAppBar";
import {Outlet} from "react-router-dom";

export default function Layout() {

    return (
        <div>
            <ApplicationAppBar/>
            <Outlet/>
        </div>
    );
}

