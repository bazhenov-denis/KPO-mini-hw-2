import styles from './NewAnimalsZone.module.css';
import { useZoo } from '../../context/ZooContext';
import AnimalCard from '../AnimalCard/AnimalCard';

export default function NewAnimalsZone() {
    const { state } = useZoo();

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Зона новых животных</h2>
            <div className={styles.box}>
                {state.animals.length === 0 ? (
                    <p className={styles.placeholder}>Здесь будут отображаться новые животные</p>
                ) : (
                    <div className={styles.grid}>
                        {state.animals.map(a => <AnimalCard key={a.id} animal={a} />)}
                    </div>
                )}
            </div>
        </div>
    );
}