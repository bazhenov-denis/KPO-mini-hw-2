import React, { useState } from 'react';
import styles from './EnclosureForm.module.css';
import { useZoo } from '../../context/ZooContext';
import { Enclosure } from '../../types/enclosure';
import { useId } from '../../hooks/useId';

export default function EnclosureForm() {
    const { dispatch } = useZoo();
    const genId = useId;

    const [form, setForm] = useState({
        type: '',
        size: '',
        capacity: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!form.type || !form.size || !form.capacity) return;

        const newEnclosure: Enclosure = {
            id: genId(),
            type: form.type,
            size: form.size as Enclosure['size'],
            capacity: parseInt(form.capacity, 10),
            animalsCount: 0
        };

        dispatch({ type: 'ADD_ENCLOSURE', payload: newEnclosure });
        setForm({ type: '', size: '', capacity: '' });
    };

    return (
        <form className={styles.card} onSubmit={handleSubmit}>
            <h2 className={styles.title}>Добавить вольер</h2>
            <input
                name="type"
                value={form.type}
                onChange={handleChange}
                className={styles.input}
                placeholder="Тип"
            />
            <select
                name="size"
                value={form.size}
                onChange={handleChange}
                className={styles.input}
            >
                <option value="">Размер</option>
                <option value="Малый">Малый</option>
                <option value="Средний">Средний</option>
                <option value="Большой">Большой</option>
            </select>
            <input
                name="capacity"
                type="number"
                value={form.capacity}
                onChange={handleChange}
                className={styles.input}
                placeholder="Максимальная вместимость"
            />
            <button type="submit" className={styles.submit}>Создать</button>
        </form>
    );
}