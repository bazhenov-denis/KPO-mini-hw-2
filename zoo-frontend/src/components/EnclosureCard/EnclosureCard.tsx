import styles from './EnclosureCard.module.css';
import { Enclosure } from '../../types/enclosure';
import { useZoo } from '../../context/ZooContext';

interface Props {
    enclosure: Enclosure;
}

export default function EnclosureCard({ enclosure }: Props) {
    const { dispatch } = useZoo();

    const feed = () => dispatch({ type: 'FEED_ENCLOSURE', payload: { id: enclosure.id } });
    const treat = () => dispatch({ type: 'TREAT_ENCLOSURE', payload: { id: enclosure.id } });
    const move = () => alert('Реализуйте модалку перемещения');

    return (
        <div className={styles.card}>
            <div className={styles.infoButton}>i</div>
            <h3 className={styles.title}>Вольер</h3>
            <p><span className={styles.label}>Тип:</span> {enclosure.type}</p>
            <p><span className={styles.label}>Размер:</span> {enclosure.size}</p>
            <p><span className={styles.label}>Животных:</span> {enclosure.animalsCount} / {enclosure.capacity}</p>
            <div className={styles.actions}>
                <button className={styles.feed} onClick={feed}>Кормить</button>
                <button className={styles.treat} onClick={treat}>Лечить</button>
                <button className={styles.move} onClick={move}>Переместить</button>
            </div>
        </div>
    );
}