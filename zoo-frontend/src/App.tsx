import AnimalForm from './components/AnimalForm/AnimalForm';
import NewAnimalsZone from './components/NewAnimalsZone/NewAnimalsZone';
import EnclosureCard from './components/EnclosureCard/EnclosureCard';
import EnclosureForm from './components/EnclosureForm/EnclosureForm';
import FeedingScheduleButton from './components/FeedingScheduleButton/FeedingScheduleButton';
import { useZoo } from './context/ZooContext';
import styles from './App.module.css';

function App() {
    const { state } = useZoo();

    return (
        <div className={styles.wrapper}>
            <h1 className={styles.heading}>Управление зоопарком</h1>

            <div className={styles.topGrid}>
                <AnimalForm />
                <NewAnimalsZone />
            </div>

            <div className={styles.scheduleBtn}><FeedingScheduleButton /></div>

            <h2 className={styles.subHeading}>Вольеры</h2>
            <div className={styles.enclosuresGrid}>
                {state.enclosures.map(e => <EnclosureCard key={e.id} enclosure={e} />)}
            </div>

            <EnclosureForm />
        </div>
    );
}

export default App;