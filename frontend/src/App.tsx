
import {Outlet} from "react-router-dom";
import Footer from "./Components/Footer";
import hintergrund from "./image/hintergrund.jpg"
import {Component} from "react";

class App extends Component {
    render() {
        const background = {
            backgroundImage: `url(${hintergrund})`,
            height: '100vh',
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat'
        }

        return (
            <div style={background}>
                <Outlet/>
                <Footer/>
            </div>
        );
    }
}

export default App;
