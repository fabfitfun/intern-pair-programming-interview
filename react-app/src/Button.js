import React, { Component } from 'react';
import IconButton from 'material-ui/IconButton';
import skull from './skull-and-bones.svg'

const buttonStyle = {

  borderRadius: '50%',
  width: 120,
  height: 120,
  padding: 30,
  position: 'relative'
};

const iconStyle = {
  width: 60,
  height: 60,
  margin: 0,
  padding: 0,
  position: 'absolute',
  top: '25%',
  left: '25%'
}

class App extends Component {
  constructor(props){
    super(props);
  }

  render() {
    return (
          <div className="button-of-doom">
            <IconButton
                iconStyle={iconStyle}
                style={buttonStyle}
                onClick={this.props.handleButtonClick}
                disabled={this.props.disabled}
            >
              {/*<img src={skull} className="skull" alt="logo" />*/}
            </IconButton>
            {/*<IconButton className='button-of-doom' buttonStyle={ style } onClick={this.handleClick} backgroundColor='#a41020' icon={<img src={skull} className="skull" alt="logo" />} />*/}
          </div>
    );
  }
}

export default App;
