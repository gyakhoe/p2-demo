import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import 'bootstrap/dist/css/bootstrap.css' // Need this for bootstrap, used on app so it can be used on other child components.
import Login from './components/account/login'
import Register from './components/account/register'
import Teams from './components/team/teams'
import Users from './components/user/users'

function App() {


  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='/register' element={<Register />} />
          <Route path='/teams' element={<Teams />} />
          <Route path='/users' element={<Users />} />

        </Routes>
      </BrowserRouter >
    </>
  )
}

export default App
