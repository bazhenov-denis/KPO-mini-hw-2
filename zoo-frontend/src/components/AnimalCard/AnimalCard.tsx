import styles from './AnimalCard.module.css';
import { Animal } from '../../types/animal';

interface Props {
    animal: Animal;
}

export default function AnimalCard({ animal }: Props) {
    return (
        <div className={styles.card}>
            <h3 className={styles.name}>{animal.name}</h3>
            <p><span className={styles.label}>Вид:</span> {animal.species}</p>
            <p><span className={styles.label}>Дата рождения:</span> {animal.birthDate}</p>
            <p><span className={styles.label}>Пол:</span> {animal.gender}</p>
            <p><span className={styles.label}>Любимая еда:</span> {animal.favouriteFood}</p>
            <p>
                <span className={styles.label}>Статус:</span>
                <span className={animal.status === 'Здоров' ? styles.healthy : styles.sick}>
          {animal.status}
        </span>
            </p>
        </div>
    );
}