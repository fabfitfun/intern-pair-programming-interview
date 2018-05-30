import React, { Component } from 'react';
import './stylesheets/avengers.css'

class App extends Component {
  render() {
    return (
      <div className="wrapper">
        <div className="hulk">
          <div className="head"><div className="mouth"/></div>
          <div className="right-arm"/>
          <div className="fist"/>
          <div className="left-arm"/>
          <div className="body"/>
          <div className="right-leg"/>
          <div className="left-leg"/>
        </div>

        <div className="captain">
          <div className="head">A</div>
          <div className="body"><div className="star"/></div>
          <div className="arms"/>
          <div className="shield"><div className="star"/></div>
          <div className="hands"/>
          <div className="legs"/>
          <div className="boots"/>
        </div>

        <div className="ironman">
          <div className="helmet"><div className="mask"/></div>
          <div className="right-arm"/>
          <div className="left-arm"/>
          <div className="body"><div className="power"/></div>
          <div className="right-leg"/>
          <div className="left-leg"/>
        </div>

        <div className="thor">
          <div className="helmet"/>
          <div className="head">
            <div className="beard"/>
          </div>
          <div className="cap"/>
          <div className="arm-right"/>
          <div className="arm-left"/>
          <div className="body"/>
          <div className="hammer"/>
          <div className="legs"/>
          <div className="feet"/>
        </div>
      </div>

    );
  }
}

export default App;