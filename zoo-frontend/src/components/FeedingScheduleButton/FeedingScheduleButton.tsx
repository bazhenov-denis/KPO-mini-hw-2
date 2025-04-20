import styles from './FeedingScheduleButton.module.css';

export default function FeedingScheduleButton() {
    const openSchedule = () => alert('Расписание кормления будет отображено здесь');
    return (
        <button className={styles.button} onClick={openSchedule}>
            🍽️&nbsp;Посмотреть расписание кормления
        </button>
    );
}