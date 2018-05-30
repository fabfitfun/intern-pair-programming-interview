import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Button from './Button'
import BoardRow from './GameBoard'
import './stylesheets/App.css';

class App extends Component {


  constructor(props){
    super(props);
    this.handleButtonClick = this.handleButtonClick.bind(this);
    this.handleCellClick = this.handleCellClick.bind(this);

    let initialGrid = [
      [0,0,0,0,0,0,0,0,0,0,0],
      [0,0,0,1,1,0,0,0,0,0,0],
      [0,0,0,0,1,0,0,0,0,0,0],
      [0,0,0,0,0,0,0,0,0,0,0],
      [0,0,0,0,0,0,0,0,0,0,0],
      [0,0,0,1,1,0,0,0,0,0,0],
      [0,0,1,1,0,0,0,0,0,0,0],
      [0,0,0,0,0,1,0,0,0,0,0],
      [0,0,0,0,1,0,0,0,0,0,0],
      [0,0,0,0,0,0,0,0,0,0,0]
    ];

    this.state = {
      grid: initialGrid
    }
  }

  handleButtonClick(e){
    e.preventDefault();

    let headers = new Headers();

    headers.append("Content-Type", "application/json");

    fetch('http://0.0.0.0:8080/gameOfLife', {
      method: 'PUT',
      headers: headers,
      body: JSON.stringify({
        grid: this.state.grid
      })
    })
    .then((response) => {
      if(response.status === 200){
        return response.json();
      }
    })
    .then((response)=> {
      this.setState({
        grid: response.grid
      })
    })
    .catch((error) => {
      console.log("error: " + error)
    });
  }

  handleCellClick(e){
    const {rowIndex, colIndex} = e.target.dataset;
    console.log(e.target.dataset);
  }

  render() {
    return (
        <MuiThemeProvider>
          <div className="row">

            <Button disabled={false} handleButtonClick={this.handleButtonClick}/>

            <div className="board">
              {this.state.grid.map((row, index) => {
                return <BoardRow rowData={row} rowIndex={index} clickHandler={this.handleCellClick} />
              })}
            </div>

          </div>
        </MuiThemeProvider>
    );
  }
}

export default App;
