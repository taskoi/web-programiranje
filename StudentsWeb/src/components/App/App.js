import React, { Component } from 'react';
import './App.css';

import { BrowserRouter, Route, Link } from 'react-router-dom';
import Home from '../Home/Home';
import StudentHOC from '../StudentHOC/StudentHOC';
import StudyProgramHOC from '../StudyProgramHOC/StudyProgramHOC';

const App = () => {

  
        return (
            <div className="container-fluid">

                <BrowserRouter>
                    <div>
                        <nav className="navbar navbar-expand-sm bg-light">
                            <header className="navbar-nav">
                                <Link to="/" className="nav-link">Home</Link>
                                <Link to="/students" className="nav-link">Students List</Link>
                                <Link to="/study_programs" className="nav-link">Study program</Link>
                            </header>
                        </nav>
                        <Route path="/" exact component={Home} />
                        <Route path="/students" component={StudentHOC} />
                        <Route path="/study_programs" component={StudyProgramHOC} />
                    </div>
                </BrowserRouter>

            </div>
        )
    
}

export default App;
