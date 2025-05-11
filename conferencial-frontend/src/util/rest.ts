const BASE_URL = 'http://localhost:8080';

async function handleResponse<T=unknown>(response: Response): Promise<T> {
  if (!response.ok) {
    const error = await response.text();
    throw new Error(error || 'Unknown error occurred');
  }
  return await response.json();
}

export async function getRequest(url: string, options?: RequestInit): Promise<Response> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });
  return await handleResponse(response);
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

export async function patchRequest<T= unknown>(url: string, data: unknown, options?: RequestInit): Promise<T> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
    ...options,
  });
  return await handleResponse<T>(response);
}

export async function deleteRequest(url: string, options?: RequestInit): Promise<Response> {
  const response = await fetch(`${BASE_URL}${url}`, {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });
  return await handleResponse(response);
}
