const BASE_URL = 'http://localhost:8080'; // Change as needed

async function handleResponse<T>(response: Response): Promise<T> {
  if (!response.ok) {
    const error = await response.text();
    throw new Error(error || 'Unknown error occurred');
  }
  return await response.json();
}

export async function getRequest<T = unknown>(url: string, options?: RequestInit): Promise<T> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });
  return await handleResponse<T>(response);
}

export async function postRequest<T = unknown>(url: string, data: unknown, options?: RequestInit): Promise<T> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
    ...options,
  });
  return await handleResponse<T>(response);
}

export async function patchRequest<T = unknown>(url: string, data: unknown, options?: RequestInit): Promise<T> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
    ...options,
  });
  return await handleResponse<T>(response);
}

export async function deleteRequest<T = unknown>(url: string, options?: RequestInit): Promise<T> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });
  return await handleResponse<T>(response);
}
