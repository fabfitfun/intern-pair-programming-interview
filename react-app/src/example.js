import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import ButtonOfDoom from './Button'
import Avengers from './Avengers'
import './stylesheets/App.css';

const tokenPermissions = {
  1: 0,
  2: 1,
  3: 2
};

class App extends Component {
  state = {
    token: 1,
    personName: "Person 1",
    permissionType: tokenPermissions[1],
    message: ""
  };

  constructor(props){
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.getContent = this.getContent.bind(this);
  }

  handleChange(e, index, value){
    e.preventDefault();

    this.setState({
      token: value,
      personName: e.target.innerHTML,
      permissionType: tokenPermissions[value],
      message: ""
    })

  }

  handleClick(e){
    e.preventDefault();

    let headers = new Headers();

      headers.append("FFF-Auth", this.state.token);
      headers.append("Content-Type", "application/json");


    fetch(`http://localhost:8080/blueberry/${this.state.token}/`, {
      method: 'GET',
      headers: headers,
      cache: "no-store"
    })
    .then((response) => {
      if(response.status === 200){
        return response.json();
      }
    })
    .then((response)=> {
      this.setState({
        message: decodeURI(response.first_name)
      })
    })
    .catch((error) => {
      console.log("error: " + error)
    });
  }

  getContent(){
    console.log(this.state.permissionType);
    switch(this.state.permissionType){
      case 2:
        return <div>
          <Avengers/>
          <h1 className="center">{this.state.message}</h1>
        </div>;
        break;
      case 1:
        return <h1>{this.state.message}</h1>
        break;
      case 0:
      default:
        return <h1>Sorry, you do not have permission</h1>;
        break;
    }
  }

  render() {
    return (
        <MuiThemeProvider>
          <div className="row">
            <SelectField
               className="select"
               value={this.state.token}
               onChange={this.handleChange}
            >
              <MenuItem value={1} primaryText="Person 1" />
              <MenuItem value={2} primaryText="Person 2" />
              <MenuItem value={3} primaryText="Person 3" />

            </SelectField>

            <ButtonOfDoom handleClick={this.handleClick}  disabled={this.state.permissionType < 1}/>
            {this.getContent()}

          </div>
        </MuiThemeProvider>
    );
  }
}

export default App;
