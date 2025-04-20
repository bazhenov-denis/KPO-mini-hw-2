let counter = 0;
export function useId() {
    return (++counter).toString();
}