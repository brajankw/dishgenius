import logo from './logo.svg';
import './App.css';

function App() {
  useEffect( () => {
  console.log("Hey, I've loaded up.")
  }, []);
  return <div>Hi there</div>;
}

export default App;
