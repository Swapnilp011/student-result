import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import StudentList from './components/StudentList';
import AddStudent from './components/AddStudent';
import AddMarks from './components/AddMarks';
import ViewResult from './components/ViewResult';
import Topper from './components/Topper';

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route exact path="/" component={StudentList} />
          <Route path="/add-student" component={AddStudent} />
          <Route path="/add-marks/:id" component={AddMarks} />
          <Route path="/view-result/:id" component={ViewResult} />
          <Route path="/topper" component={Topper} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;