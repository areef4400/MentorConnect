import './App.css'
import {Routes, Route} from 'react-router-dom'

import Login from './pages/Login'
import Signup from './pages/Signup'
import Mentors from './pages/Mentors'
import Sessions from './pages/Sessions'
import Profile from './pages/Profile'
import Home from './pages/Home'
import References from './components/footerlinks/References'
import About from './components/footerlinks/About'
import TermsAndConditions from './components/footerlinks/TermsAndConditions'
import { useEffect, useState } from "react"

function App() {
  const [login, setLogin] = useState(false);
  
  useEffect(()=>{
      const storedItem = localStorage.getItem("token");
      if(storedItem){
          setLogin(true);
      }else{
        setLogin(false)
      }
  },[])

  return (
      <>
            {
              login?
                <Routes>
                    <Route path='/' element={<Home setLogin={setLogin} login={login}/>}/>
                    <Route path='/home' element={<Home setLogin={setLogin} login={login}/>}/>
                    <Route path="/mentors" element={<Mentors />} />
                    <Route path="/sessions" element={<Sessions />} />
                    <Route path="/profile" element={<Profile setLogin={setLogin}/>} />

                    <Route path="/about" element={<About />} />
                    <Route path="/t&c" element={<TermsAndConditions />} />
                    <Route path="/references" element={<References />} />
                    
                    <Route path="/*" element={<Home setLogin={setLogin} login={login}/>} />
                </Routes>
              :
                <Routes>
                    <Route path="/login" element={<Login setLogin={setLogin}/>} />
                    <Route path="/signup" element={<Signup setLogin={setLogin}/>} />

                    <Route path="/*" element={<Login setLogin={setLogin}/>} />
                </Routes>
            }
      </>
  )
}

export default App
