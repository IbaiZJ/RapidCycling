// Header and sidebar of appLayout: mobile sidebar toggle and the dropdowns.
document.addEventListener('DOMContentLoaded', () => {
  const sidebar = document.getElementById('sidebar');
  const toggleButton = document.getElementById('toggleSidebar');
  const mainSection = document.getElementById('main');
  const dropdownButtons = document.querySelectorAll('[data-dropdown]');

  const isMobile = () => window.innerWidth < 768;
  const isSidebarOpen = () => !sidebar.classList.contains('-translate-x-full');

  function closeSidebar() {
    sidebar.classList.add('-translate-x-full');
    mainSection.classList.remove('blur-xs');
  }

  function closeDropdowns() {
    dropdownButtons.forEach((button) => {
      document.getElementById(button.dataset.dropdown).classList.add('hidden');
    });
  }

  // Sidebar (only hidden on mobile)
  toggleButton.addEventListener('click', () => {
    sidebar.classList.toggle('-translate-x-full');
    mainSection.classList.toggle('blur-xs', isSidebarOpen());
  });

  mainSection.addEventListener('click', () => {
    if (isMobile() && isSidebarOpen()) {
      closeSidebar();
    }
  });

  window.addEventListener('resize', () => {
    if (!isMobile()) {
      mainSection.classList.remove('blur-xs');
    }
  });

  // Dropdowns: a button with data-dropdown="someId" opens the element with id="someId"
  dropdownButtons.forEach((button) => {
    button.addEventListener('click', (e) => {
      e.stopPropagation();
      const menu = document.getElementById(button.dataset.dropdown);
      const wasOpen = !menu.classList.contains('hidden');
      closeDropdowns();
      menu.classList.toggle('hidden', wasOpen);
    });
  });

  document.addEventListener('click', closeDropdowns);
});
