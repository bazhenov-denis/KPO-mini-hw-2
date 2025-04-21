import React, { useState, useEffect } from 'react';
import styles from './AnimalForm.module.css';
import { isAxiosError } from 'axios';
import { useZoo } from '../../context/ZooContext';
import { Animal, Gender, HealthStatus } from '../../types/animal';
import { fetchAnimalSpecies, fetchFoodTypes } from '../../controllers/TypesController';
import { createAnimal, AnimalRequest } from '../../controllers/AnimalController';

export default function AnimalForm() {
    const { dispatch } = useZoo();

    // Form state
    const [form, setForm] = useState<{
        species: string;
        name: string;
        birthDate: string;
        gender: Gender | '';
        favouriteFood: string;
        status: HealthStatus | '';
    }>({
        species: '',
        name: '',
        birthDate: '',
        gender: '',
        favouriteFood: '',
        status: ''
    });

    // Dictionaries
    const [speciesOptions, setSpeciesOptions] = useState<string[]>([]);
    const [foodOptions, setFoodOptions] = useState<string[]>([]);
    const [loadingDicts, setLoadingDicts] = useState(false);
    const [dictError, setDictError] = useState<string | null>(null);

    // Submission state
    const [saving, setSaving] = useState(false);
    const [saveError, setSaveError] = useState<string | null>(null);

    // Load species and food types
    useEffect(() => {
        async function load() {
            try {
                setLoadingDicts(true);
                const [species, foods] = await Promise.all([
                    fetchAnimalSpecies(),
                    fetchFoodTypes()
                ]);
                setSpeciesOptions(species);
                setFoodOptions(foods);
            } catch (err: unknown) {
                let msg = 'Ошибка загрузки справочников';
                if (isAxiosError(err) && err.response) msg = String(err.response.data);
                else if (err instanceof Error) msg = err.message;
                setDictError(msg);
            } finally {
                setLoadingDicts(false);
            }
        }
        load();
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
        setSaveError(null);
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!form.species || !form.name || !form.status) {
            setSaveError('Заполните обязательные поля');
            return;
        }

        const req: AnimalRequest = {
            name: form.name,
            species: form.species,
            birthDate: form.birthDate,
            gender: form.gender === 'Женский' ? 'FEMALE' : 'MALE',
            favoriteFood: form.favouriteFood,
            status: form.status === 'Болен' ? 'SICK' : 'HEALTHY'
        };

        try {
            setSaving(true);
            const created = await createAnimal(req);
            const local: Animal = {
                id: created.id.toString(),
                species: created.species,
                name: created.name,
                birthDate: created.birthDate,
                gender: created.gender === 'FEMALE' ? 'Женский' : 'Мужской',
                favouriteFood: created.favoriteFood,
                status: created.status === 'SICK' ? 'Болен' : 'Здоров'
            };
            dispatch({ type: 'ADD_ANIMAL', payload: local });
            setForm({ species: '', name: '', birthDate: '', gender: '', favouriteFood: '', status: '' });
        } catch (err: unknown) {
            let msg = 'Ошибка создания животного';
            if (isAxiosError(err) && err.response) msg = String(err.response.data);
            else if (err instanceof Error) msg = err.message;
            setSaveError(msg);
        } finally {
            setSaving(false);
        }
    };

    if (loadingDicts) return <p>Загрузка справочников…</p>;
    if (dictError) return <p className={styles.error}>{dictError}</p>;

    return (
        <form className={styles.card} onSubmit={handleSubmit}>
            <h2 className={styles.title}>Добавить новое животное</h2>

            <select name="species" value={form.species} onChange={handleChange} className={styles.input}>
                <option value="">Вид *</option>
                {speciesOptions.map(s => <option key={s} value={s}>{s}</option>)}
            </select>

            <input
                name="name"
                value={form.name}
                onChange={handleChange}
                placeholder="Кличка *"
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

            <select name="favouriteFood" value={form.favouriteFood} onChange={handleChange} className={styles.input}>
                <option value="">Любимая еда</option>
                {foodOptions.map(f => <option key={f} value={f}>{f}</option>)}
            </select>

            <select name="status" value={form.status} onChange={handleChange} className={styles.input}>
                <option value="">Статус *</option>
                <option value="Здоров">Здоров</option>
                <option value="Болен">Болен</option>
            </select>

            {saveError && <p className={styles.error}>{saveError}</p>}

            <button type="submit" className={styles.submit} disabled={saving}>
                {saving ? 'Сохраняем…' : 'Создать'}
            </button>
        </form>
    );
}
