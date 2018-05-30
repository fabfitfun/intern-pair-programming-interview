import React from 'react'

const BoardRow = ({rowData, ...rest}) => {
  return <div className="board-row">
      {rowData.map( (cell, index) => {
      return<BoardCell cell={cell} colIndex={index} {...rest} />;
    })}
  </div>
}

const BoardCell = ({cell, colIndex, rowIndex, clickHandler}) => {
  return (
      <div className={`cell ${cell === 1 ? 'live-cell' : ''}`} data-rowIndex={rowIndex} data-colIndex={colIndex} onClick={clickHandler}/>
      );
}

const onClick = (e) => {
  const {rowIndex, colIndex} = e.target.dataset;
  console.log(e.target.dataset);
}

export default BoardRow;
