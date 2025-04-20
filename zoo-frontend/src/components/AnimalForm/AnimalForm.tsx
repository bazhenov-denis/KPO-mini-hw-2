import React, { useState } from 'react';
import styles from './AnimalForm.module.css';
import { useZoo } from '../../context/ZooContext';
import { Animal, Gender, HealthStatus } from '../../types/animal';
import { useId } from '../../hooks/useId';

export default function AnimalForm() {
    const { dispatch } = useZoo();
    const genId = useId;

    const [form, setForm] = useState({
        species: '',
        name: '',
        birthDate: '',
        gender: '' as Gender | '',
        favouriteFood: '',
        status: '' as HealthStatus | ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!form.species || !form.name) return;

        const newAnimal: Animal = {
            id: genId(),
            species: form.species,
            name: form.name,
            birthDate: form.birthDate,
            gender: form.gender as Gender,
            favouriteFood: form.favouriteFood,
            status: form.status as HealthStatus
        };

        dispatch({ type: 'ADD_ANIMAL', payload: newAnimal });

        setForm({ species: '', name: '', birthDate: '', gender: '', favouriteFood: '', status: '' });
    };

    return (
        <form className={styles.card} onSubmit={handleSubmit}>
            <h2 className={styles.title}>Добавить новое животное</h2>

            <input
                name="species"
                value={form.species}
                onChange={handleChange}
                placeholder="Вид"
                className={styles.input}
            />
            <input
                name="name"
                value={form.name}
                onChange={handleChange}
                placeholder="Кличка"
                className={styles.input}
            />
            <input
                type="date"
                name="birthDate"
                value={form.birthDate}
                onChange={handleChange}
                className={styles.input}
            />
            <select name="gender" value={form.gender} onChange={handleChange} className={styles.input}>
                <option value="">Пол</option>
                <option value="Мужской">Мужской</option>
                <option value="Женский">Женский</option>
            </select>
            <input
                name="favouriteFood"
                value={form.favouriteFood}
                onChange={handleChange}
                placeholder="Любимая еда"
                className={styles.input}
            />
            <select name="status" value={form.status} onChange={handleChange} className={styles.input}>
                <option value="">Статус</option>
                <option value="Здоров">Здоров</option>
                <option value="Болен">Болен</option>
            </select>

            <button type="submit" className={styles.submit}>Создать</button>
        </form>
    );
}