document.addEventListener('DOMContentLoaded', function() {
    setupCustomSelects();

});

function setupCustomSelects() {
    const customSelects = document.querySelectorAll('.custom-select');
    
    customSelects.forEach(select => {
        const trigger = select.querySelector('.select-trigger');
        const options = select.querySelectorAll('.option');
        const selectedText = select.querySelector('.selected-option');
        
        trigger.addEventListener('click', () => {
            customSelects.forEach(otherSelect => {
                if (otherSelect !== select) {
                    otherSelect.classList.remove('open');
                    const otherTrigger = otherSelect.querySelector('.select-trigger');
                    otherTrigger.setAttribute('aria-expanded', 'false');
                }
            });
            
            select.classList.toggle('open');
            const isExpanded = select.classList.contains('open');
            trigger.setAttribute('aria-expanded', isExpanded);
            
            if (isExpanded) {
                const firstOption = options[0];
                if (firstOption) firstOption.focus();
            }
        });
        
        options.forEach(option => {
            option.addEventListener('click', () => {
                selectedText.textContent = option.textContent;
                select.classList.remove('open');
                trigger.setAttribute('aria-expanded', 'false');
                trigger.focus();
                
                options.forEach(opt => {
                    opt.setAttribute('aria-selected', 'false');
                });
                option.setAttribute('aria-selected', 'true');
            });
                        
        });
    });
    
    document.addEventListener('click', (e) => {
        const target = e.target;
        const isSelect = target.closest('.custom-select');
        
        if (!isSelect) {
            customSelects.forEach(select => {
                select.classList.remove('open');
                const trigger = select.querySelector('.select-trigger');
                trigger.setAttribute('aria-expanded', 'false');
            });
        }
    });
}