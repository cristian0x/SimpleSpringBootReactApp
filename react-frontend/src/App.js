import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ListEmployee from "./components/ListEmployee";
import AddEmployee from "./components/AddEmployee";

function App() {
  return (
    <div>
      <Router>
        <Header />
        <div className="container">
          <Switch>
            <Route exact path="/" component={ListEmployee} />
            <Route path="/employees" component={ListEmployee} />
            <Route path="/add-employee" component={AddEmployee} />
            <Route path="/edit-employee/:id" component={AddEmployee} />
            <Route path="*">
              <div className="page404">
                <h5>404</h5>
                <h5>WE ARE SORRY, BUT THE PAGE YOU REQUESTED WAS NOT FOUND</h5>
              </div>
            </Route>
          </Switch>
        </div>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
