export type Gender = 'Мужской' | 'Женский';
export type HealthStatus = 'Здоров' | 'Болен';

export interface Animal {
    id: string;
    species: string;
    name: string;
    birthDate: string; // ISO‑строка вида «YYYY‑MM‑DD»
    gender: Gender;
    favouriteFood: string;
    status: HealthStatus;
    enclosureId?: string;      // Идентификатор вольера (если животное уже перемещено)
}