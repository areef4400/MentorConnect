import './Subscription.css'
function Subscription(){

    const email = localStorage.getItem("email");
    const starterPack = 10;
    const proffPack = 15;
    const enterPack = 21;
    
    const handleStarter = async () =>{
        try{
            const responce = await fetch(`http://localhost:8080/user/increamentSession/${email}/${starterPack}`,{
                method : "POST",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization": "Bearer "+localStorage.getItem("token"),
                },
            })

            const data = await responce.text();
            
            if(data.trim() == "Session Increamented"){
                alert("Entrolled Succesfully!");
            }else{
                alert("Some Backend Issue")
            }
        }catch{
            alert("Failed to Entroll Course!!");
        }
    }

    const handleProff = async() =>{
        try{
            const responce = await fetch(`http://localhost:8080/user/increamentSession/${email}/${proffPack}`,{
                method : "POST",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization": "Bearer "+localStorage.getItem("token"),
                },
            })

            const data = await responce.text();
            if(data.trim()  == "Session Increamented"){
                alert("Entrolled Succesfully!");
            }else{
                alert("Some Backend Issue")
            }
        }catch{
            alert("Failed to Entroll Course!!");
        }
    }

    const handleEnter = async() => {
        try{
            const responce = await fetch(`http://localhost:8080/user/increamentSession/${email}/${enterPack}`,{
                method : "POST",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization": "Bearer "+localStorage.getItem("token"),
                },
            })

            const data = await responce.text();
            
            if(data.trim()  == "Session Increamented"){
                alert("Entrolled Succesfully!");
            }else{
                alert("Some Backend Issue")
            }
        }catch{
            alert("Failed to Entroll Course!!");
        }
    }

    return(
        <>  
        <div className="all-cards">
            <div className="subscription-card">
                <div className="headings">
                    <h2>Starter Pack</h2>
                    <h5>999 for 10 Sessions</h5>
                </div>
                <div className="inlcuded-items">
                    <h4>What Included</h4>
                    <ul>
                        <li>1-on-1 Video Sessions</li>
                        <li>Career Guidance</li>
                        <li>Resume Review</li>
                        <li>Email Support</li>
                    </ul>
                </div>
                <button onClick={handleStarter}>Entroll Now</button>
            </div>

            <div className="subscription-card">
                <div className="headings">
                    <h2>Professional Pack</h2>
                    <h5>2499 for 15 Sessions</h5>
                </div>
                <div className="inlcuded-items">
                    <h4>What Included</h4>
                    <ul>
                        <li>1-on-1 Video Sessions</li>
                        <li>Career Guidance</li>
                        <li>Resume Review</li>
                        <li>Email Support</li>
                        <li>Priority Support</li>
                        <li>Interview Prep</li>
                    </ul>
                </div>
                <button onClick={handleProff}>Entroll Now</button>
            </div>

            <div className="subscription-card">
                <div className="headings">
                    <h2>EnterPrise Pack</h2>
                    <h5>4999 for 21 Sessions</h5>
                </div>
                <div className="inlcuded-items">
                    <h4>What Included</h4>
                    <ul>
                        <li>1-on-1 Video Sessions</li>
                        <li>Career Guidance</li>
                        <li>Resume Review</li>
                        <li>Email Support</li>
                        <li>24/7 Support</li>
                        <li>Priority Support</li>
                        <li>Interview Prep</li>
                    </ul>
                </div>
                <button onClick={handleEnter} >Entroll Now</button>
            </div>
        </div>
        </>
    )
}

export default Subscription;