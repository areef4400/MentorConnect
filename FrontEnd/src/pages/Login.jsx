import { NavLink, useActionData, useNavigate } from "react-router-dom";
import { useActionState } from "react";
import './Login.css'

function Login({setLogin}){
    const navigate = useNavigate();

    const HandleLogin = async(prevData, formData)  =>{
        let email = formData.get("username");
        let password = formData.get("password");
        
        
        if(!(email && password)){
            await new Promise(res => setTimeout(res, 1000));
            return {error : "Insuffient Data"}
        }else{

            const response = await fetch(`http://localhost:8080/auth/login/${email}/${password}`,{
                method : "POST",
                headers : {
                    "Content-Type": "application/json",
                },
                body : JSON.stringify({
                    email,
                    password,
                }),
            })

            // console.log(response);
            if(response.ok){
                const data = await response.json();
                const token = data.token;
                await new Promise(res => setTimeout(res, 2000));
                localStorage.setItem("email", email);
                localStorage.setItem("token", token);
                setLogin(true);
                navigate("/home")
                if(token != null){
                }
            }else{
                await new Promise(res => setTimeout(res, 2000));
                return {error : "Failed to Login"}
            }
        }

    }

    const [data, action ,pending] = useActionState(HandleLogin, undefined);

    return (
        <>  
            <form action={action}>
                <div className="login-page">
                    <h1>Login Page</h1>
                    <input type="text" placeholder="Email" name="username"/>
                    <input type="password" placeholder="Password" name="password"/>
                    <div className="buttons">
                        <button disabled = {pending} id="submit">Login</button>
                            <div className="signuplink">
                                Already have Account <NavLink to='/signup'> SignUp</NavLink>
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

export default Login