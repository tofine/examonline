import React from "react"
import { Link } from "@reach/router"

import fetchApi from "@/api"

import "../styles/main.scss"

import logo from "@/assets/images/logo.png"

export default class Main extends React.Component {
  constructor(props) {
    super(props)
    this.state = {}
  }

  fetchApi = async () => {
    let data = await fetchApi.getImages({ index: 6 })
    console.log(data);
    this.setState({
      data: data || {},
    })
  }

  componentDidMount() {
    this.fetchApi()
  }

  render() {
    const { data = {} } = this.state
    return (
      <div className="wtdc" style={{ backgroundImage: `url(${data.url})` }}>
        <header className="wtdc-header">
          WTDC FE GATSBY DEMO
        </header>
        <section className="wtdc-section">
          <img src={logo} alt=""/>
        </section>

        <section>
          Edit Me and Show The Magic!
        </section>

        <footer className='wtdc-footer'>
          <Link to='form'>点我有意想不到的效果哟</Link>

        </footer>
      </div>
    )
  }
}
