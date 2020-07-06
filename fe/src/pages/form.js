import React from 'react';
import '@/styles/form.scss';

export default class Form extends React.PureComponent {

  state = {
    count: 1  //计数
  }

  showMessage = () => {
    alert(this.state.count);
  }

  componentDidMount() {
    this.interVal = setInterval(() => this.setState({
      count: this.state.count + 1
    }), 1000)
  }

  componentWillUnmount() {
    window.clearInterval(this.interVal)
  }

  render() {
    return <div className="content">
      test page
      <p onClick={this.showMessage}>其实除了一张图，啥也没有！</p>
    </div>
  }

}