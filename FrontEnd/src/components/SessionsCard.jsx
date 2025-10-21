import { useEffect, useState } from 'react';
import './SessionsCard.css'

function SessionsCard({sessionDate, mentor, sessionTime}){
    return (
        <>
            <div className="session-card">
                <h5>Date :{sessionDate}</h5>
                <h5>Time : {sessionTime}</h5>
                <h5>{mentor}</h5>
            </div>
        </>
    )
}

export default SessionsCard;
