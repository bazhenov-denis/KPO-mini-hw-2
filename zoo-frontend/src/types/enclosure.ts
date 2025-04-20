export interface Enclosure {
    id: string;
    type: string;
    size: 'Малый' | 'Средний' | 'Большой';
    capacity: number;          // Максимальная вместимость
    animalsCount: number;      // Текущее количество животных
}