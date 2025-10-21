import './UpComingCard.css'
import { useNavigate } from 'react-router-dom';

function UpComingCard({date, time, mentor, zoomLink}){

    const navigate = useNavigate();

    const handleJoin = () => {
        try {
            if (zoomLink) {
                window.open(zoomLink, "_blank");
            } else {
                alert("No Zoom link available");
                navigate("/mentors");
            }
        } catch (err) {
            alert("Error opening Zoom link");
            navigate("/mentors");
        }
    };
    return (
        <div className="upc-card">
            <h5>{date}</h5>
            <h5>{time}</h5>
            <h5>{mentor}</h5>
            <button onClick={handleJoin}>Join Zoom</button>
        </div>
    )

}
export default UpComingCard