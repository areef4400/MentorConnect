import './Login.css'
import { useActionState } from "react";
import { useNavigate, NavLink } from "react-router-dom";

function Signup({setLogin}){
    const navigate = useNavigate();
    
    const HandleSignup = async(prevData, formData)  =>{
        let name = formData.get("username");
        let email = formData.get("email");
        let password = formData.get("password");
        
        
        if(!(name && email && password)){
            await new Promise(res => setTimeout(res, 1000));
            return {error : "Insuffient Data"}
        }else{

            try{
                const response = await fetch(`http://localhost:8080/auth/signup`,{
                    method : "POST",
                    headers :{
                        "Content-Type" : "application/json",
                    },
                    body : JSON.stringify({
                        "username" : name,
                        "email": email,
                        "password":password,
                        "availableSession":0
                    }),
                })   
                if(response.ok){
                    const data = await response.json();
                    const token = data.token;
    
                    if(token != null){
                        localStorage.setItem("email", email);
                        localStorage.setItem("token", token)
                        await new Promise(res => setTimeout(res, 2000));
                        setLogin(true);
                        navigate("/home")
                    }
                }else{
                    alert("Error while Signing Up!!!")
                    navigate("/signup");
                }

            }catch{
                await new Promise(res => setTimeout(res, 2000));
                return {error : "Registration Failed"}

            }
        }

    }

    const [data, action ,pending] = useActionState(HandleSignup, undefined);

    return (
        <>  
            <form action={action}>
                <div className="login-page">
                    <h1>SignUp Page</h1>
                    <input type="text" placeholder="Username" name = "username"/>
                    <input type="text" placeholder="Email" name="email"/>
                    <input type="password" placeholder="Password" name="password"/>
                    <div className="buttons">
                        <button disabled = {pending} id="submit">Sign Up</button>
                            <div className="signuplink">
                                Already have Account <NavLink to='/login'> Login</NavLink>
                            </div>
                    </div>
                    {
                        data?.error?<h3 style={{
                            color:'red'
                        }}>
                            {data.error}
                        </h3> : null
                    }
                </div>
            </form>
        </>
    )
}

export default Signup